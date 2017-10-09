/*
 * Copyright 2015-2017 Lukas Krejci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 *
 */

package org.revapi.java.matcher;

import javax.lang.model.element.Parameterizable;

import org.revapi.ElementMatcher.Result;
import org.revapi.java.spi.JavaAnnotationElement;
import org.revapi.java.spi.JavaModelElement;

/**
 * @author Lukas Krejci
 */
final class NumberOfTypeParametersExpression implements MatchExpression {
    private final ComparisonOperator operator;
    private final int expected;

    NumberOfTypeParametersExpression(ComparisonOperator operator, int expected) {
        this.operator = operator;
        this.expected = expected;
    }

    @Override
    public Result matches(JavaModelElement element) {
        if (!(element.getDeclaringElement() instanceof Parameterizable)) {
            return Result.DOESNT_MATCH;
        }

        Parameterizable el = (Parameterizable) element.getDeclaringElement();

        int nofArguments = el.getTypeParameters().size();

        return Result.fromBoolean(operator.evaluate(nofArguments, expected));
    }

    @Override
    public Result matches(JavaAnnotationElement annotation) {
        return Result.DOESNT_MATCH;
    }

    @Override
    public Result matches(AnnotationAttributeElement attribute) {
        return Result.DOESNT_MATCH;
    }

    @Override
    public Result matches(TypeParameterElement typeParameter) {
        return Result.DOESNT_MATCH;
    }
}