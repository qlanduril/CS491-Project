package com.bitir.menu.service;

import com.bitir.menu.dto.MenuRequest;
import com.bitir.menu.dto.MenuResponse;
import com.bitir.menu.model.Menu;
import com.bitir.menu.repository.MenuRepository;
import com.bitir.menu.service.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {
    private final MenuRepository menuRepository;


    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = MenuMapper.mapRequestToMenu(menuRequest);

        menuRepository.save(menu);
        log.info("Menu {} named {} is saved.", menu.getId(), menu.getName());

        // TODO: Add check if list of items actually exist and return null if not.
        return MenuMapper.mapMenuToResponse(menu);
    }


    public MenuResponse getMenuById(Integer menuId) {
        Menu menu = menuRepository.findMenuById(menuId);

        if(menu == null) {
            log.info("Menu {} does not exist.", menuId);
            return null;
        }

        log.info("Menu {} named {} is found.", menu.getId(), menu.getName());
        return MenuMapper.mapMenuToResponse(menu);
    }


    public List<MenuResponse> getAllMenus() {
        List<Menu> allMenus = (List<Menu>) menuRepository.findAll();
        log.info("{} number of menus are queried.", allMenus.size());
        return allMenus.stream().map(MenuMapper::mapMenuToResponse).toList();
    }


    public MenuResponse updateMenu(Integer menuId, MenuRequest menuRequest) {
        Menu menu = menuRepository.findMenuById(menuId);

        if(menu == null) {
            log.info("Menu {} does not exist.", menuId);
            return null;
        }
        if(menuRequest.getName() != null) {
            menu.setName(menuRequest.getName());
        }
        // TODO: Add check if list of items actually exist and return null if not.
        if(menuRequest.getItems() != null) {
            menu.setItems(menuRequest.getItems());
        }

        menuRepository.save(menu);
        log.info("Menu {} is updated.", menu.getId());

        return MenuMapper.mapMenuToResponse(menu);
    }


    public MenuResponse deleteMenuById(Integer menuId) {
        Menu menu = menuRepository.deleteMenuById(menuId);

        if(menu == null) {
            log.info("Menu {} does not exist.", menuId);
            return null;
        }

        log.info("Menu {} is deleted.", menu.getId());
        return MenuMapper.mapMenuToResponse(menu);
    }

}
