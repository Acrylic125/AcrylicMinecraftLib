package com.acrylic.universal.files.configloader;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see ConfigValue
 * @see ConfigLoader
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Configurable {

    /**
     * @return The file path.
     */
    @NotNull
    String filePath();

    /**
     * The name of the plugin that this file is rooted from.
     *
     * If there is no plugin specified (If this is left as default
     * or empty), the config file will be based on the root directory
     * of the server.
     *
     * Let's say the plugin is specified as "SamplePlugin", the
     * file will be placed in ".../plugins/SamplePlugin/<file path>.
     *
     * If there is no plugin specified, the file will be placed in
     * ".../<file path>".
     *
     * @return The plugin name.
     */
    @NotNull
    String plugin() default "";

    /**
     *
     * @return The plugin that contains the resource file.
     */
    String resourcePlugin() default "";

    /**
     *
     * @return The resource path from the resource plugin.
     */
    String resourcePath() default "";

    /**
     *
     * @return Whether the parent classes will be affected by this
     * annotation.
     */
    boolean loadWithParentClass() default false;

    /**
     * Classifies as the main root of what you are
     * trying to retrieve.
     *
     * For instance, if this is specified as "test",
     * all variable retrievals will be rooted based on
     * the test category.
     *
     * For example,
     * The root is "test",
     *
     * sample.yml:
     * test:
     *   a: true
     *   b: 1000
     * test2:
     *   value-that-cannot-be-retrieved: true
     *
     * When you specify a {@link ConfigValue} of path,
     * "a", it will declare that variable as true.
     *
     * Likewise, if you specify a config value of path,
     * "b",it will declare that variable as 1000.
     *
     * However, since "test2" is out of the root, you
     * cannot retrieve the config value "value-that-cannot-be-retrieved".
     *
     * @return The root.
     */
    String[] root() default {};

}
