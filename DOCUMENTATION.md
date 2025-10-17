# Visual Block Age (VBA) - Technical Documentation

This document provides a technical overview of the Visual Block Age mod, including the original implementation instructions and a guide for resource pack developers.

## Original Implementation Instructions

This section contains the original project specification that was used to develop the Visual Block Age mod.

---

### Project Specification: Client-Side Block Age Tracker Mod

#### Project Title: Visual Block Age (VBA)

##### 1. Introduction

This document details the requirements for a client-side Minecraft mod for Fabric 1.21.10. The mod, named "Visual Block Age" (VBA), will enable resource pack developers to create dynamic block models based on the estimated age of certain blocks. The primary initial target is the cactus block, which has an internal age property that is not correctly synchronized to the client, preventing resource packs from using it for model variations. This mod will use client-side logic to track block placement and random ticks to estimate the age property and expose this information for resource packs. The mod will use owo-lib for its configuration menu.

##### 2. Mod Objectives

*   **Track Block Placement:** Listen for `BlockPlaceEvent` on the client side to record the initial placement time of specific blocks.
*   **Approximate Block Ticks:** For each tracked block, estimate its internal age by counting random ticks based on gameplay time.
*   **Provide Block State Information:** Modify how the game's block render system sees specific blocks, overriding the vanilla age property with the mod's client-side estimate.
*   **Enable Custom Resource Packs:** Allow resource pack creators to use the `age` property in blockstate files (e.g., `cactus.json`) to create visual variations without requiring server-side modification.
*   **Implement Config Menu:** Use owo-lib to create a configuration menu, accessible via Mod Menu, to enable/disable the mod and configure block tracking.
*   **Expandable Architecture:** Design the mod to be easily expanded to track other block types with hidden or improperly synced block states (e.g., sugar cane).

---

## Guide for Resource Pack Developers

The Visual Block Age mod makes it possible to create resource packs that change the appearance of blocks as they "age." This is done by using the `age` property in a block's `blockstates` file.

### Example: Cactus Blockstate

Here is an example of how you can use the `age` property to create a cactus that changes its appearance as it gets older. In this example, you would need to create 16 different models for the cactus, one for each age from 0 to 15.

```json
{
  "variants": {
    "age=0": { "model": "minecraft:block/cactus_age_0" },
    "age=1": { "model": "minecraft:block/cactus_age_1" },
    "age=2": { "model": "minecraft:block/cactus_age_2" },
    "age=3": { "model": "minecraft:block/cactus_age_3" },
    "age=4": { "model": "minecraft:block/cactus_age_4" },
    "age=5": { "model": "minecraft:block/cactus_age_5" },
    "age=6": { "model": "minecraft:block/cactus_age_6" },
    "age=7": { "model": "minecraft:block/cactus_age_7" },
    "age=8": { "model": "minecraft:block/cactus_age_8" },
    "age=9": { "model": "minecraft:block/cactus_age_9" },
    "age=10": { "model": "minecraft:block/cactus_age_10" },
    "age=11": { "model": "minecraft:block/cactus_age_11" },
    "age=12": { "model": "minecraft:block/cactus_age_12" },
    "age=13": { "model": "minecraft:block/cactus_age_13" },
    "age=14": { "model": "minecraft:block/cactus_age_14" },
    "age=15": { "model": "minecraft:block/cactus_age_15" }
  }
}
```

You would then need to create the corresponding model files (e.g., `cactus_age_0.json`, `cactus_age_1.json`, etc.) in your resource pack's `models/block` directory.
