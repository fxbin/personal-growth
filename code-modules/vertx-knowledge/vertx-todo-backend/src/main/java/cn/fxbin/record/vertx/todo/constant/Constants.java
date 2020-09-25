package cn.fxbin.record.vertx.todo.constant;

/**
 * Constants
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/9/14 19:01
 */
public interface Constants {

    /** API Route */
    String API_GET = "/todos/:todoId";

    String API_LIST_ALL = "/todos";

    String API_CREATE = "/todos";

    String API_UPDATE = "/todos/:todoId";

    String API_DELETE = "/todos/:todoId";

    String API_DELETE_ALL = "/todos";


    String REDIS_TODO_KEY = "VERT_TODO";

}
