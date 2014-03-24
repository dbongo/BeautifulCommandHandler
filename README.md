# Beautiful Command Handler

This is a demo project in Java, showing that it's possible to write clean code
for command line parsing. The code simply describes what they're doing.

If one wants to extend the list of commands, simply implements the 
`CommandLogic` interface in the package `commandHandler.parser.impl`, and use a
static block to register an instance of Parser, which would parse its command.

And everything else are gracefully handled :)
