/*
 * Copyright (c) 2015 Andrew Coates
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
 * limitations under the License.
 */

package org.datalorax.populace.graph.inspector;

import com.google.common.collect.ImmutableSet;
import org.datalorax.populace.field.RawField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * An inspector that exposes objects as having fields, but no child elements
 *
 * @author Andrew Coates - 28/02/2015.
 */
public class ObjectInspector implements Inspector {
    public static final Inspector INSTANCE = new ObjectInspector();

    @Override
    public Iterable<RawField> getFields(final Object instance) {
        final List<RawField> fields = new ArrayList<>();
        for (Field field : instance.getClass().getDeclaredFields()) {
            fields.add(new RawField(field));
        }
        return ImmutableSet.copyOf(fields);
    }

    @Override
    public boolean equals(final Object that) {
        return this == that || (that != null && getClass() == that.getClass());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
