package org.example.app.controller;

import org.example.app.service.UsersService;
import org.example.app.utils.AppStarter;
import org.example.app.view.*;

public class UsersController {

    UsersService service = new UsersService();

    public void create() {
        UserCreateView view = new UserCreateView();
        view.getOutput(service.create(view.getData()));
        AppStarter.startApp();
    }

    public void read() {
        UsersReadView view = new UsersReadView();
        view.getOutput(service.read());
        AppStarter.startApp();
    }

    public void update() {
        UsersUpdateView view = new UsersUpdateView();
        view.getOutput(service.update(view.getData()));
        AppStarter.startApp();
    }

    public void delete() {
        UsersDeleteView view = new UsersDeleteView();
        view.getOutput(service.delete(view.getData()));
        AppStarter.startApp();
    }

    public void readById() {
        UsersReadByIdView view = new UsersReadByIdView();
        view.getOutput(service.readById(view.getData()));
        AppStarter.startApp();
    }
}
