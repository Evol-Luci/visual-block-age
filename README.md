# Visual Block Age (VBA)

Visual Block Age is a client-side Fabric mod for Minecraft that allows resource pack developers to create dynamic block models based on the estimated age of certain blocks. This mod is ideal for creating more immersive and visually interesting worlds.

## How it Works

The mod tracks the placement of configurable blocks and estimates their age based on the in-game time. This age is then exposed to the game's rendering engine, allowing resource packs to use the `age` property in their blockstate files to display different models for different ages.

## Installation

1.  **Install Fabric:** If you haven't already, install the [Fabric Loader](https://fabricmc.net/use/).
2.  **Install Fabric API:** Download the [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) and place it in your `mods` folder.
3.  **Install owo-lib:** Download [owo-lib](https://www.curseforge.com/minecraft/mc-mods/owo-lib) and place it in your `mods` folder.
4.  **Install Visual Block Age:** Download the latest release of Visual Block Age and place it in your `mods` folder.

## Configuration

The mod can be configured through the Mod Menu. The following options are available:

*   **Enable/Disable:** Globally enable or disable the mod.
*   **Tick Rate:** The number of game ticks it takes for a block to age by one stage.
*   **Tracked Blocks:** A list of block IDs to track. By default, this is set to `minecraft:cactus`.

## For Resource Pack Developers

To use the age property in your resource packs, you can create a `blockstates` file that references different models based on the block's age. See the `example_cactus_blockstate.json` file for an example.

## Contributing

Contributions are welcome! If you have any ideas or suggestions, please open an issue or submit a pull request on the [GitHub repository](https://github.com/vba_dev/visualblockage).
