# MinecraftModSkeleton
#### A helpful base layout for anyone looking to write a Minecraft mod!

_This branch is intended for Minecraft version 1.12 and is bundled with scripts for forge(1.12.2 - 14.23.5.2768). See other branches for older versions, or see the "bare" branch for the source with no forge files included._

### How to initialize modding environment for IntelliJ idea
_steps copied from the [Forge Documentation](https://mcforge.readthedocs.io/en/latest/gettingstarted/#terminal-free-intellij-idea-configuration) (Section: "Terminal-free IntelliJ IDEA configuration")_

1. Fork or download this repository
2. Launch IntelliJ IDEA and choose to open/import the build.gradle file, using the default gradle wrapper choice for the project's JVM. Also make sure that you have "create separate module per source set" **un**checked. Wait for the gradle configuration to sync into intellij once the project is open.
3. Open the gradle tab on the right side of IntelliJ. Run the _setupDecompWorkspace_ task (inside the forgegradle task group). It will take a few minutes, and use quite a bit of RAM. 
4. Once the setup task is done, you will want to run the _genIntellijRuns_ task, which will configure the project’s run/debug targets.
After it’s done, you should click the blue refresh icon on the gradle panel (there’s another refresh icon on the main toolbar, but that’s not it). This will re-synchronize the IDEA project with the Gradle data, making sure that all the dependencies and settings are up to date.
5. Finally, assuming you use IDEA 2016 or newer, you will have to fix the classpath module. Go to Edit configurations and in both Minecraft Client and Minecraft Server, change Use classpath of module to point to the task _<ModName>.main_ .
  
If all the steps worked correctly, you should now be able to choose the Minecraft run tasks from the dropdown, and select "Minecraft Client" to run your code!
