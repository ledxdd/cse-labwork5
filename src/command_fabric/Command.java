package cse_labwork5.src.command_fabric;

import java.io.IOException;

public interface Command {
    void execute(String arg) throws IOException;
}