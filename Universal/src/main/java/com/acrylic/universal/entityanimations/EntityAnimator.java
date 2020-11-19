package com.acrylic.universal.entityanimations;

import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.utils.TeleportationUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAnimator {

    Entity getBukkitEntity();

    default EntityAnimator name(@Nullable String name) {
        getBukkitEntity().setCustomName(ChatUtils.get(name));
        return nameVisible(name != null);
    }

    default EntityAnimator nameVisible(boolean b) {
        getBukkitEntity().setCustomNameVisible(b);
        return this;
    }

    default void teleport(Location location) {
        TeleportationUtils.tp(getBukkitEntity(), location);
    }

    default void setEquipment(AbstractEntityEquipmentBuilder entityEquipment) {
        Entity entity = getBukkitEntity();
        if (entity instanceof LivingEntity) {
            entityEquipment.apply((LivingEntity) entity);
        }
    }

    @SuppressWarnings("unchecked")
    static <T extends Entity> T spawnEntity(@NotNull Location location, EntityType entity) {
        World world = location.getWorld();
        assert world != null;
        return (T) world.spawnEntity(location, entity);
    }

}
