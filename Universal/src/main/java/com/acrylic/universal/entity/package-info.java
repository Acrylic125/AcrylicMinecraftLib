/**
 * Why this?
 *
 * This package is to be used for handling custom entities
 * by acting as a wrapper for Bukkit/NMS entities.
 *
 * This package provides lots of prebuilt functionalities
 * and customisations for the wrapped entity.
 *
 * These classes serves as the following:
 * - Cross version compatibility wherever possible.
 *   In most cases, entities could have changed functionality
 *   (be it deprecated methods, change of method names etc) which
 *   could provide a hurdle in creating cross version compatible code.
 *   This package tries to resolve that by having a standardise set
 *   of methods to perform equivalent (but not guaranteed) functionalities.
 *
 *   This has been done and expanded upon in the AcrylicNMSLib project:
 *   https://github.com/Acrylic125/AcrylicNMSLibV2
 * - Base functionalities that predefines the entity.
 *   Although this is greatly limited and it is discouraged that you
 *   try to extend upon this due to the multitude of possible variation
 *   of classes of a given entity, (It is preferred that you hold an instance
 *   of a given entity instance instead.) you can still use predefined
 *   functions to create a standardise entity instance.
 *
 *   For example,
 *   {@link com.acrylic.universal.entity.ArmorStandInstance#asAnimator()}
 * - To provide more extensible features than the normal Bukkit Entities
 *   would allow.
 *   This has been done and expanded upon in the AcrylicNMSLib project:
 *   https://github.com/Acrylic125/AcrylicNMSLibV2
 *
 *
 * Some entities are declared as 'OLD' with the Old entity
 * suffix in the class name. These entities are NOT deprecated
 * but are not supported in a later version of MC. Theses should
 * only be used for it's intended version.
 *
 * @see com.acrylic.universal.entity.WitherSkeletonEntityInstanceOld
 * @see com.acrylic.universal.entity.ZombieVillagerEntityInstanceOld
 */

package com.acrylic.universal.entity;