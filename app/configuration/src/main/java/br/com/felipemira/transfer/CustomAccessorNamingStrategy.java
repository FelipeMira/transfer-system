package br.com.felipemira.transfer;


import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.util.IntrospectorUtils;

import javax.lang.model.element.ExecutableElement;

public class CustomAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

	@Override
	public boolean isSetterMethod(ExecutableElement method) {
		if (super.isSetterMethod(method)) {
			return true;
		}

		String methodName = method.getSimpleName().toString();
		return methodName.startsWith("with") && methodName.length() > 4;
	}


	@Override
	public String getPropertyName(ExecutableElement getterOrSetterMethod) {
		String methodName = getterOrSetterMethod.getSimpleName().toString();
		return methodName.startsWith("with") ? IntrospectorUtils.decapitalize(methodName.substring(4)) :
				super.getPropertyName(getterOrSetterMethod);
	}

}
