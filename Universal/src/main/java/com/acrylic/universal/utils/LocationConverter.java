package com.acrylic.universal.utils;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LocationConverter extends TextTemplate {

    public static LocationConverter DEFAULT = new LocationConverter();

    static {
        DEFAULT.setSuffixFormat("&7");
    }

    private String xSuffix = "x";
    private String ySuffix = "y";
    private String zSuffix = "z";
    private String spacing = ", ";
    private String worldSpacing = "in world ";

    public String getxSuffix() {
        return xSuffix;
    }

    public LocationConverter setxSuffix(String xSuffix) {
        this.xSuffix = xSuffix;
        return this;
    }

    public String getySuffix() {
        return ySuffix;
    }

    public LocationConverter setySuffix(String ySuffix) {
        this.ySuffix = ySuffix;
        return this;
    }

    public String getzSuffix() {
        return zSuffix;
    }

    public LocationConverter setzSuffix(String zSuffix) {
        this.zSuffix = zSuffix;
        return this;
    }

    public String getSpacing() {
        return spacing;
    }

    public LocationConverter setSpacing(String spacing) {
        this.spacing = spacing;
        return this;
    }

    public String getWorldSpacing() {
        return worldSpacing;
    }

    public LocationConverter setWorldSpacing(String worldSpacing) {
        this.worldSpacing = worldSpacing;
        return this;
    }

    private String constructXYZ(@NotNull Location location, int dp) {
        return (dp == 0) ?
                "&r" + getValueFormat() + (int) location.getX() + getSuffixFormat() + xSuffix + spacing +
                "&r" + getValueFormat() + (int) location.getY() + getSuffixFormat() + ySuffix + spacing +
                "&r" + getValueFormat() + (int) location.getZ() + getSuffixFormat() + zSuffix
                :
                "&r" + getValueFormat() + MathUtils.round(location.getX(), dp) + getSuffixFormat() + xSuffix + spacing +
                "&r" + getValueFormat() + MathUtils.round(location.getY(), dp) + getSuffixFormat() + ySuffix + spacing +
                "&r" + getValueFormat() + MathUtils.round(location.getZ(), dp) + getSuffixFormat() + zSuffix;
    }

    private String constructWorld(@NotNull Location location) {
        return getSuffixFormat() + worldSpacing + "&r" + getValueFormat() + Objects.requireNonNull(location.getWorld()).getName();
    }

    public String convert(@NotNull Location location) {
        return convert(location, 2);
    }

    public String convert(@NotNull Location location, int dp) {
        return ChatUtils.get(constructXYZ(location, dp) + "&r");
    }

    public String convertWithWorld(@NotNull Location location) {
        return convertWithWorld(location, 2);
    }

    public String convertWithWorld(@NotNull Location location, int dp) {
        return ChatUtils.get(constructXYZ(location, dp) + spacing + constructWorld(location) + "&r");
    }


}
