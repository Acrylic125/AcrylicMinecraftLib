package com.acrylic.universal.entityanimations;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entityanimations.equipment.EntityEquipmentBuilder;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface LivingEntityAnimator extends EntityAnimator {

    LivingEntity getBukkitEntity();

    default void setEquipment(EntityEquipmentBuilder entityEquipment) {
        entityEquipment.apply(getBukkitEntity());
    }

    default LivingEntityAnimator ai(boolean b) {
        if (Universal.getAcrylicPlugin().getVersionStore().isLegacyVersion()) {
            NBTEntity nbtEntity = new NBTEntity(getBukkitEntity());
            nbtEntity.setByte("NoAI", (byte) ((b) ? 0 : 1));
        } else
            getBukkitEntity().setAI(b);
        return this;
    }

    default LivingEntityAnimator visible(boolean b) {
        if (b)
            getBukkitEntity().removePotionEffect(PotionEffectType.INVISIBILITY);
        else
            getBukkitEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 1, false, false));
        return this;
    }

}
