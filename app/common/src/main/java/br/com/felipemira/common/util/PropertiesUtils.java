package br.com.felipemira.common.util;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.util.Collections.singletonMap;

public class PropertiesUtils {

    public static Properties properties;

    public static Properties loadProperties(String[] args){
        if(Objects.isNull(properties)){
            String profile = Objects.isNull(System.getProperties().get("spring.profiles.active"))?
                    null : (String) System.getProperties().get("spring.profiles.active");
            if(Objects.isNull(properties)){
                for (String arg : args) {
                    if (arg.startsWith("--spring.profiles.active=")) {
                        profile = arg.split("=")[1];
                        break;
                    }
                }
            }
            try{
                return loadProperties(profile);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return properties;
    }

    private static Properties loadProperties(String profile) throws FileNotFoundException {
        properties = new Properties();

        boolean yml = !String.valueOf(PropertiesUtils.class.getClassLoader().getResource("application.yml")).equals("null");
        boolean yaml = !String.valueOf(PropertiesUtils.class.getClassLoader().getResource("application.yaml")).equals("null");
        boolean propertiesFile = !String.valueOf(PropertiesUtils.class.getClassLoader().getResource("application.properties")).equals("null");

        if(yml || yaml){
            return loadPropertiesYaml(yml, profile);
        }else if(propertiesFile){
            return loadPropertiesProp(profile);
        }else{
            throw new FileNotFoundException("Arquivo nao encontrado, coloque dentro de resources com a extensao .properties, .yaml ou .yml");
        }
    }

    private static Properties loadPropertiesYaml(boolean yml, String profile){
        Yaml yaml = new Yaml();
        String profileInProperties = "";
        String extension = yml? ".yml" : ".yaml";

        properties.putAll(getFlattenedMap(yaml.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("application" + extension))));

        if(!String.valueOf(properties.get("spring.profiles.active")).equals("null")){
            profileInProperties = properties.get("spring.profiles.active").toString();
            if(profileInProperties.contains("default")){
                profileInProperties = profileInProperties.split(",")[1].trim();
            }
        }
        if(profile != null){
            properties.putAll(getFlattenedMap(yaml.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("application-" + profile + extension))));
        }else if(!profileInProperties.isEmpty()){
            properties.putAll(getFlattenedMap(yaml.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("application-" + profileInProperties + extension))));
        }
        return properties;
    }

    private static Properties loadPropertiesProp(String profile){
        String profileInProperties = "";

        loadFileProperties(properties, Objects.requireNonNull(PropertiesUtils.class.getClassLoader().getResource("application.properties")).getPath());

        if(!String.valueOf(properties.get("spring.profiles.active")).equals("null")){
            profileInProperties = properties.get("spring.profiles.active").toString();
            if(profileInProperties.contains("default")){
                profileInProperties = profileInProperties.split(",")[1].trim();
            }
        }
        if(profile != null){
            loadFileProperties(properties, Objects.requireNonNull(PropertiesUtils.class.getClassLoader().getResource("application-" + profile + "properties")).getPath());
        }else if(!profileInProperties.isEmpty()){
            loadFileProperties(properties, Objects.requireNonNull(PropertiesUtils.class.getClassLoader().getResource("application-" + profileInProperties + "properties")).getPath());
        }
        return properties;
    }

    private static final Map<String, Object> getFlattenedMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<>();
        buildFlattenedMap(result, source, null);
        return result;
    }

    @SuppressWarnings("unchecked")
    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String path) {
        source.forEach((key, value) -> {
            if (!String.valueOf(path).equals("null"))
                key = path + (key.startsWith("[") ? key : '.' + key);
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                buildFlattenedMap(result, (Map<String, Object>) value, key);
            } else if (value instanceof Collection) {
                int count = 0;
                for (Object object : (Collection<?>) value)
                    buildFlattenedMap(result, singletonMap("[" + (count++) + "]", object), key);
            } else {
                result.put(key, value != null ? value : "");
            }
        });
    }

    private static void loadFileProperties(Properties properties, String pathProperties){
        try{
            File file = new File(pathProperties);
            FileReader fileReader = new FileReader(file);
            properties.load(fileReader);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
