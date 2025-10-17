#### Project Specification: Client-Side Block Age Tracker Mod

### Project Title: Visual Block Age (VBA)

## 1. Introduction

This document details the requirements for a client-side Minecraft mod for Fabric 1.21.10. The mod, named "Visual Block Age" (VBA), will enable resource pack developers to create dynamic block models based on the estimated age of certain blocks. The primary initial target is the cactus block, which has an internal age property that is not correctly synchronized to the client, preventing resource packs from using it for model variations. This mod will use client-side logic to track block placement and random ticks to estimate the age property and expose this information for resource packs. The mod will use owo-lib for its configuration menu.

## 2. Mod Objectives

Track Block Placement: Listen for BlockPlaceEvent on the client side to record the initial placement time of specific blocks.
Approximate Block Ticks: For each tracked block, estimate its internal age by counting random ticks based on gameplay time.
Provide Block State Information: Modify how the game's block render system sees specific blocks, overriding the vanilla age property with the mod's client-side estimate.
Enable Custom Resource Packs: Allow resource pack creators to use the age property in blockstate files (e.g., cactus.json) to create visual variations without requiring server-side modification.
Implement Config Menu: Use owo-lib to create a configuration menu, accessible via Mod Menu, to enable/disable the mod and configure block tracking.
Expandable Architecture: Design the mod to be easily expanded to track other block types with hidden or improperly synced block states (e.g., sugar cane).

## 3. Technical Implementation Details

# 3.1 Dependencies

Fabric Loader: 0.17.23
Minecraft: 1.21.10
Fabric API: The latest version compatible with Minecraft 1.21.10.
owo-lib: The latest version compatible with Fabric 1.21.10.

# 3.2 Key Components

ClientWorldTickHandler
Purpose: A class that handles listening for world and player events on the client side.
Functionality:
On Block Placement: When the client places a block that the mod is configured to track (e.g., minecraft:cactus), it records the block's position and the current game time.
On Block Destruction: When a tracked block is broken, its data is removed from the tracking list.
On Chunk Load/Unload: Handles persisting and loading tracked block data as chunks are loaded and unloaded to prevent data loss.
BlockAgeTracker
Purpose: A manager class responsible for storing and updating the age of all tracked blocks in the client's world.
Functionality:
Storage: Uses a hash map (Map<BlockPos, BlockAgeData>) to store block positions and their associated tracking data.
Age Estimation: On every client tick, iterates through all tracked blocks and updates their estimated age. The age is incremented randomly, mirroring the server's "random tick" logic. The tick rate can be adjusted in the mod's configuration.
Model Property Injection: Provides a method to retrieve the estimated age for a given block position.
CustomBlockStateProvider
Purpose: A mixin or alternative method to inject the custom age property into the block render system.
Functionality:
Override Block State: Intercepts calls to retrieve the block state for a block being rendered.
Inject Age: For tracked blocks, it uses the BlockAgeTracker to get the estimated age and creates a new BlockState with the custom age property set, returning this modified state to the renderer.
ResourcePackCompatibility
Purpose: The mod must not create a new block ID. Instead, it must expose the estimated age to the vanilla cactus block's render system.
Technical Details: The CustomBlockStateProvider component is crucial for this. It must modify the vanilla BlockState just before rendering to include the age property, which the resource pack's cactus.json can then utilize.
ModConfiguration
Purpose: Manages the mod's settings, providing a user-friendly interface.
Functionality:
Configuration File: Stores settings (e.g., enabled/disabled, random tick speed, list of tracked blocks) in a JSON or similar format.
owo-lib Integration: Creates a config screen using owo-lib that lists the supported blocks, allowing users to enable or disable tracking for each one individually.

# 4. Expansion Plan (Future Blocks)

The mod should be built with expansion in mind. To add new blocks, the developer should only need to:
Add Block ID to Config: Update the configuration file and owo-lib config screen to include the new block ID (e.g., minecraft:sugar_cane).
Define Growth Logic: Create a class or function to handle the age-tracking logic specific to the new block, if it differs from the cactus. For example, sugar cane has a different growth cycle.
Update Event Listener: Ensure the ClientWorldTickHandler handles events related to the new block type.

# 5. Testing and Validation

Single-player Test: Verify that the visual age progression works correctly in a single-player world.
Multiplayer Test: Confirm that the visual age progression is tracked locally and does not cause issues when connecting to a vanilla server.
Resource Pack Integration: Test with a custom resource pack that uses the age property in the cactus.json to ensure the correct models are displayed.
Config UI Test: Ensure the owo-lib configuration menu functions as expected and that changes are saved and loaded correctly.

# 6. File Structure

/src/main/java/com/your_username/visualblockage/
├── VisualBlockAge.java (Mod Entrypoint)
├── config/
│   ├── ModConfig.java (owo-lib config class)
│   └── ConfigManager.java
├── rendering/
│   ├── CustomBlockStateProvider.java (Mixin or custom rendering hook)
└── tracking/
    ├── BlockAgeTracker.java
    └── BlockAgeData.java
/src/main/resources/
├── assets/visualblockage/
│   ├── lang/en_us.json
│   └── owo_config.json
├── visualblockage.mixins.json
└── fabric.mod.json

# 7. Known Limitations

No Server Sync: This mod does not communicate with the server. Therefore, the estimated age is an approximation and will not be perfectly accurate or synchronized with the actual growth on the server.
Client-Specific: The visual changes will only be visible to the player with the mod installed.
Approximate Ticking: If a player is far from a cactus for an extended period, the estimated age may not perfectly match the server's internal random tick. When the player returns, the model might abruptly change as the client "catches up" its estimation.
