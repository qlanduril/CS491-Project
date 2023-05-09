package com.bitir.menu.service.mapper;

import com.bitir.menu.dto.MenuRequest;
import com.bitir.menu.dto.MenuResponse;
import com.bitir.menu.model.Menu;

public class MenuMapper {

    public static MenuResponse mapMenuToResponse(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .items(menu.getItems())
                .build();
    }


    public static Menu mapRequestToMenu(MenuRequest menuRequest) {
        return Menu.builder()
                .name(menuRequest.getName())
                .items(menuRequest.getItems())
                .build();
    }

}
