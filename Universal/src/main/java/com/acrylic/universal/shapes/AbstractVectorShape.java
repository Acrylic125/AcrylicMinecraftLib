package com.acrylic.universal.shapes;

import com.acrylic.universal.math.Orientation2D;
import com.acrylic.universal.math.Orientation3D;
import com.acrylic.universal.math.TrigonometrySet;
import com.acrylic.universal.utils.LocationUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

@Getter
public abstract class AbstractVectorShape extends AbstractShape {

    private Orientation2D primaryTransformation;
    private Orientation3D secondaryTransformation;

    public AbstractVectorShape(float radius, int amount) {
        this(radius, amount, 0, 0, 0);
    }

    public AbstractVectorShape(float radius, int amount, float xRot, float yRot, float zRot) {
        super(radius, amount);
        primaryTransformation = new Orientation2D(0,0);
        secondaryTransformation = new Orientation3D(xRot, yRot, zRot);
    }

    public AbstractVectorShape setRot(LivingEntity entity) {
        Vector location = entity.getLocation().getDirection().normalize();
        Bukkit.broadcastMessage(location + "");
        //primaryTransformation.setXOrientation()
        return this;
    }

    public void rotate(Vector vector) {
        primaryTransformation.transform(vector);
        secondaryTransformation.transform(vector);
    }




}
