package tool_collection

import backupper.Backupper

fun main(args: Array<String>)
{
    // Get the command of the ToolCollection or default to help
    val command = if (args.isNotEmpty()) args[0] else "help"

    when (command)
    {
        "help" ->
        {
            println(
                """
                This is the ToolCollection command-line edition. It contains a lot of small tools that can be used to via command-line.
                
                Usage: toolcollection COMMAND [OPTIONS]
                
                Commands:
                    help            show this help message
                    backupper       does a backup of specified files and zips them
                """.trimIndent()
            )
        }
        "backupper" ->
        {
            Backupper(args.drop(1))
        }
        else ->
        {
            println("Error: Command '$command' not found. Type 'toolcollection help' to get information on how to use the ToolCollection")
        }
    }
}
