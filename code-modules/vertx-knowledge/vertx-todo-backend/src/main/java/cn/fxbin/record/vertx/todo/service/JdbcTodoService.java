package cn.fxbin.record.vertx.todo.service;

import cn.fxbin.record.vertx.todo.entity.Todo;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;

import java.util.List;

/**
 * JdbcTodoService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/9/16 10:37
 */
public class JdbcTodoService implements TodoService {

    private final Vertx vertx;
    private final JsonObject config;
    private final JDBCClient client;

    public JdbcTodoService(Vertx vertx, JsonObject config) {
        this.vertx = vertx;
        this.config = config;
        this.client = JDBCClient.createShared(vertx, config);
    }

    @Override
    public Completable initData() {
        return null;
    }

    @Override
    public Maybe<Todo> insert(Todo todo) {
        return null;
    }

    @Override
    public Single<List<Todo>> getAll() {
        return null;
    }

    @Override
    public Maybe<Todo> getCertain(String todoID) {
        return null;
    }

    @Override
    public Maybe<Todo> update(String todoId, Todo newTodo) {
        return null;
    }

    @Override
    public Completable delete(String todoId) {
        return null;
    }

    @Override
    public Completable deleteAll() {
        return null;
    }
}
