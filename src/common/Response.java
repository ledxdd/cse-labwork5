package common;

import common.models.SpaceMarine;

import java.io.Serializable;
import java.util.Collection;

public class Response implements Serializable {
    private static final long serialVersionUID = 2L;

    private final String message;
    private final Collection<SpaceMarine> collection;

    public Response(String message) {
        this.message = message;
        this.collection = null;
    }

    public Response(String message, Collection<SpaceMarine> collection) {
        this.message = message;
        this.collection = collection;
    }

    public String getMessage() {
        return message;
    }

    public Collection<SpaceMarine> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        return "Response {" + "message: " + message + '\'' + ", hasCollection=" + (collection != null) + '}';
    }
}
