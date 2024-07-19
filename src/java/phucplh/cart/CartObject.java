/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import phucplh.T_Product.T_ProductDTO;

/**
 *
 * @author ADMIN
 */
public class CartObject implements Serializable {

    private Map<T_ProductDTO, Integer> items;

    public Map<T_ProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(T_ProductDTO id) {
        //1. check id item is existed
        if (id == null || id.getId() == 0) {
            return;
        }
        //2.when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3.when items has existed, checking exited id
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
            //4. update items
            this.items.put(id, quantity);
        } else {
            this.items.put(id, 1);
        }

    }

    public void removeItemFromCart(String[] removedItemIds) {
//        //1. check exitsted items
//        if (this.items == null) {
//            return;
//        }
//        //2. when items has existed, ckeck existed id
//        if (this.items.containsKey(id)) {
//            this.items.remove(id);
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }
//        }
//    }

        // Kiểm tra nếu giỏ hàng và danh sách sản phẩm cần xóa không null
        if (this.items != null && removedItemIds != null) {
            for (String removedItemId : removedItemIds) {
                Iterator<Map.Entry<T_ProductDTO, Integer>> iterator = this.items.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<T_ProductDTO, Integer> entry = iterator.next();
                    T_ProductDTO product = entry.getKey();

                    // Kiểm tra nếu ID của sản phẩm trùng với ID cần xóa
                    if (String.valueOf(product.getId()).equals(removedItemId)) {
                        int quantity = entry.getValue();
                        // Xóa sản phẩm khỏi giỏ hàng nếu số lượng là 1 hoặc ít hơn
                        if (quantity <= 1) {
                            iterator.remove();
                            break; // Dừng vòng lặp nếu đã xử lý sản phẩm này
                        } else {
                            // Giảm số lượng sản phẩm trong giỏ hàng nếu số lượng lớn hơn 1
                            this.items.put(product, quantity - 1);
                        }
                    }
                }
            }
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
