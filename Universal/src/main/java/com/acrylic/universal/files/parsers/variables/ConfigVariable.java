package com.acrylic.universal.files.parsers.variables;

import com.acrylic.weights.WeightObject;

/**
 * Config variables are used for objects that supports
 * the 'variables' component.
 *
 * @see com.acrylic.universal.files.parsers.Parser
 *
 * @param <E> The return type.
 */
public interface ConfigVariable<E> extends WeightObject {

    E get();

}
