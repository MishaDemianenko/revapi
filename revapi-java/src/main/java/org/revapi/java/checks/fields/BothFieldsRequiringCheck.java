/*
 * Copyright 2014 Lukas Krejci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.revapi.java.checks.fields;

import javax.lang.model.element.VariableElement;

import org.revapi.java.TypeEnvironment;
import org.revapi.java.checks.AbstractJavaCheck;

/**
 * @author Lukas Krejci
 * @since 0.1
 */
abstract class BothFieldsRequiringCheck extends AbstractJavaCheck {

    protected boolean shouldCheck(VariableElement oldField, VariableElement newField) {
        return shouldCheck(oldField, getOldTypeEnvironment(), newField, getNewTypeEnvironment());
    }

    protected static boolean shouldCheck(VariableElement oldField, TypeEnvironment oldEnvironment,
        VariableElement newField, TypeEnvironment newEnvironment) {
        if (oldField == null || newField == null) {
            return false;
        }

        return !(isBothPrivate(oldField, newField) ||
            !isBothAccessibleOrInApi(oldField.getEnclosingElement(), oldEnvironment,
                newField.getEnclosingElement(), newEnvironment));
    }
}