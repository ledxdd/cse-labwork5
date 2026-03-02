package cse_labwork5.src.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
    void execute(String arg) throws IOException;
}