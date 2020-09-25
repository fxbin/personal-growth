package cn.fxbin.record.vertx.todo.service;

import cn.fxbin.record.vertx.todo.entity.Todo;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

/**
 * TodoService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/9/16 10:36
 */
public interface TodoService {

    Completable initData();

    Maybe<Todo> insert(Todo todo);

    Single<List<Todo>> getAll();

    Maybe<Todo> getCertain(String todoID);

    Maybe<Todo> update(String todoId, Todo newTodo);

    Completable delete(String todoId);

    Completable deleteAll();


}
