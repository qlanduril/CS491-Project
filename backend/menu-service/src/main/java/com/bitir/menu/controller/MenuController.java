package com.bitir.menu.controller;

import com.bitir.menu.dto.MenuRequest;
import com.bitir.menu.dto.MenuResponse;
import com.bitir.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse response = menuService.createMenu(menuRequest);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }


    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        List<MenuResponse> response = menuService.getAllMenus();
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping
    public ResponseEntity<MenuResponse> getMenuById(@QueryParam("menuId") Integer menuId) {
        MenuResponse response = menuService.getMenuById(menuId);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PutMapping
    public ResponseEntity<MenuResponse> updateMenu(@QueryParam("menuId") Integer menuId,
                                                   @RequestBody MenuRequest menuRequest) {
        MenuResponse response = menuService.updateMenu(menuId, menuRequest);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @DeleteMapping
    public ResponseEntity<MenuResponse> deleteMenuById(@QueryParam("menuId") Integer menuId) {
        MenuResponse response = menuService.deleteMenuById(menuId);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
