package com.acrylic.universal.files.configloader;

import org.jetbrains.annotations.NotNull;

public @interface EmbeddedConfigValue {

    @NotNull
    String[] path();

    boolean shouldSourceOtherEmbeds() default false;

    boolean shouldSourceNonEmbeds() default false;

    boolean ignoreSourceEmbedFlags() default false;


}
