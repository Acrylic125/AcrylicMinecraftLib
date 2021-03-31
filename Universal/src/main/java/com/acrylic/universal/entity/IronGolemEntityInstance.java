package com.acrylic.universal.entity;

import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Spider;
import org.jetbrains.annotations.NotNull;

public interface IronGolemEntityInstance extends LivingEntityInstance {

    @NotNull
    @Override
    IronGolem getBukkitEntity();

}
