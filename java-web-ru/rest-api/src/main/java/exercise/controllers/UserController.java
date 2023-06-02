package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
    };

    public void getOne(Context ctx, String id) {

        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
    };

    public void create(Context ctx) {
        User user = DB.json().toBean(User.class, ctx.body());
        user.save();
    };

    public void update(Context ctx, String id) {
        User user = DB.json().toBean(User.class, ctx.body());
        user.setId(id);
        user.update();
    };

    public void delete(Context ctx, String id) {
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();
        user.delete();
    };
}
