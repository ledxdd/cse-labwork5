package server.commands;

import common.Request;
import common.Response;

public interface RequestHandler {
    Response handleRequest(Request request);
}
