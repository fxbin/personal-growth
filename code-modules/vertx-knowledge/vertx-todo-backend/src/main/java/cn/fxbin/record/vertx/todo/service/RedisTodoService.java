package cn.fxbin.record.vertx.todo.service;

import cn.fxbin.record.vertx.todo.constant.Constants;
import cn.fxbin.record.vertx.todo.entity.Todo;
import io.reactivex.*;
import io.vertx.core.json.Json;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.redis.client.Redis;
import io.vertx.reactivex.redis.client.RedisAPI;
import io.vertx.reactivex.redis.client.Response;
import io.vertx.redis.client.RedisOptions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RedisTodoService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/9/16 10:37
 */
public class RedisTodoService implements TodoService {

    private final Vertx vertx;
    private final RedisOptions options;
    private final RedisAPI redis;

    public RedisTodoService(Vertx vertx, RedisOptions options) {
        this.vertx = vertx;
        this.options = options;
        this.redis = RedisAPI.api(Redis.createClient(vertx, options));
    }

    @Override
    public Completable initData() {
        Todo sample = new Todo(Math.abs(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE)),
                "Something to do...", false, 1, "todo/ex");
        return insert(sample).ignoreElement();
    }

    @Override
    public Maybe<Todo> insert(Todo todo) {
        final String encoded = Json.encodePrettily(todo);
//        return redis.rxSetnx(Constants.REDIS_TODO_KEY, encoded).map(new::todo);
        return null;
    }

    @Override
    public Single<List<Todo>> getAll() {
//        return redis.rxHvals(Constants.REDIS_TODO_KEY).map(res -> res.getKeys().stream().map(key -> res.get(key))).;

        Maybe<Response> responseMaybe = redis.rxHvals(Constants.REDIS_TODO_KEY);

        return new Single<List<Todo>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super List<Todo>> singleObserver) {

            }
        };
    }

    @Override
    public Maybe<Todo> getCertain(String todoID) {
        if (Objects.isNull(todoID)) {
            return Maybe.empty();
        }
//        return Maybe.wrap(redis.rxHget(Constants.REDIS_TODO_KEY, todoID)).map();

        Maybe<Response> responseMaybe = redis.rxHget(Constants.REDIS_TODO_KEY, todoID);
        return new Maybe<Todo>() {
            @Override
            protected void subscribeActual(MaybeObserver<? super Todo> maybeObserver) {

            }
        };
    }

    @Override
    public Maybe<Todo> update(String todoId, Todo newTodo) {
        return getCertain(todoId)
                .map(old -> old.merge(newTodo))
                .flatMap(e -> insert(e)
                        .flatMap(r -> Maybe.just(e))
                );
    }

    @Override
    public Completable delete(String todoId) {
        return Completable.fromMaybe(redis.rxHdel(Arrays.asList(Constants.REDIS_TODO_KEY, todoId)));
    }

    @Override
    public Completable deleteAll() {
        return Completable.fromMaybe(redis.rxDel(Collections.singletonList(Constants.REDIS_TODO_KEY)));
    }
}
