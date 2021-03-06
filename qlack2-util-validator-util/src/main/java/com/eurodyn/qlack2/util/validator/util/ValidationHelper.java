/*
* Copyright 2014 EUROPEAN DYNAMICS SA <info@eurodyn.com>
*
* Licensed under the EUPL, Version 1.1 only (the "License").
* You may not use this work except in compliance with the Licence.
* You may obtain a copy of the Licence at:
* https://joinup.ec.europa.eu/software/page/eupl/licence-eupl
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the Licence is distributed on an "AS IS" basis,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the Licence for the specific language governing permissions and
* limitations under the Licence.
*/
package com.eurodyn.qlack2.util.validator.util;

import com.eurodyn.qlack2.util.validator.util.errors.ValidationAttribute;
import com.eurodyn.qlack2.util.validator.util.errors.ValidationErrorType;
import com.eurodyn.qlack2.util.validator.util.errors.ValidationErrors;
import com.eurodyn.qlack2.util.validator.util.errors.ValidationFieldErrors;
import com.eurodyn.qlack2.util.validator.util.exception.QValidationException;

@Deprecated
public class ValidationHelper {
	public static void throwValidationError(String[] fields, String[] messages,
			String[] wrongValues) {
		ValidationErrors ve = new ValidationErrors();
		for (int i = 0; i < fields.length; i++) {
			ValidationFieldErrors vfe = new ValidationFieldErrors(fields[i]);
			ValidationErrorType vet = new ValidationErrorType(messages[i]);
			if (wrongValues[i] != null) {
				vet.putAttribute(ValidationAttribute.InvalidValue, wrongValues[i]);
			}

			if (messages[i] != null) {
				vet.putAttribute(ValidationAttribute.Message, messages[i]);
			}
			vfe.addError(vet);
			ve.addValidationError(vfe);
		}

		throw new QValidationException(ve);
	}
}
