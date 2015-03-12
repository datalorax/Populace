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

package org.datalorax.populace.field.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.datalorax.populace.field.FieldInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A combination field filter that only evaluates to true if all child filters evaluate true
 * @author Andrew Coates - 28/02/2015.
 */
public class AllFieldFilter implements FieldFilter {
    private final List<FieldFilter> filters;

    public AllFieldFilter(final FieldFilter first, final FieldFilter... theRest) {
        final List<FieldFilter> fieldFilters = new ArrayList<>();
        fieldFilters.add(first);
        fieldFilters.addAll(Arrays.asList(theRest));

        for (FieldFilter filter : fieldFilters) {
            Validate.notNull(filter, "at least one filter was null");
        }

        filters = Collections.unmodifiableList(fieldFilters);
    }

    @Override
    public boolean include(final FieldInfo field) {
        for (FieldFilter filter : filters) {
            if (!filter.include(field)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AllFieldFilter that = (AllFieldFilter) o;
        return this.filters.size() == that.filters.size() && this.filters.containsAll(that.filters);
    }

    @Override
    public int hashCode() {
        return filters.hashCode();
    }

    @Override
    public String toString() {
        return "ALL {" +  StringUtils.join(filters, ',') + "}";
    }
}
