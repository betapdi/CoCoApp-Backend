package com.server.cocoapp.dto;

import java.util.List;

import com.server.cocoapp.classes.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    String id;
    List<CartItem> items;
}
