package com.web.bookStore.resources;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookStore.dto.BookDTO;
import com.web.bookStore.dto.CartDTO;
import com.web.bookStore.dto.CartItemDTO;
import com.web.bookStore.entities.Cart;
import com.web.bookStore.entities.CartItem;
import com.web.bookStore.entities.User;
import com.web.bookStore.request.ProductRequest;
import com.web.bookStore.services.BookService;
import com.web.bookStore.services.CartItemService;
import com.web.bookStore.services.CartService;
import com.web.bookStore.services.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:52747")
@RequestMapping("/cart")
public class CartResource {

	@Autowired
	private BookService bookService;

	@Autowired // tu tao doi tuong cho minh xai ma khong can new
	private UserService uService;

	@Autowired
	private CartService cartSer;
	
	@Autowired
	private CartItemService cartItemService;

	// pt loadCart
	@GetMapping("/user/{userId}")
	public ResponseEntity<CartDTO> getCartByUserId(@PathVariable String userId) {
		// kiem tra phien dang nhap cua user nao lay cart cua user do
		User user = uService.findUserByID(userId);
		Cart cart = cartSer.getCartById(user.getUserCart().getId()).get();
		Set<CartItemDTO> cartItemDTO = new HashSet<>();
		Set<CartItem> cartItem = cart.getCartItems();
		for (CartItem c : cartItem) {
			BookDTO bDTO = new BookDTO(c.getBook().getId(), c.getBook().getTitle(), c.getBook().getOurPrice(),c.getBook().getBookImage());
			CartItemDTO cDTO = new CartItemDTO(c.getQuantity(),bDTO);
			cartItemDTO.add(cDTO);
		}
		CartDTO cartDTO = new CartDTO(cart.getId(), cartItemDTO);

		return ResponseEntity.ok(cartDTO);
	}

	// pt them san pham vao gio hang
	@PostMapping("/addProduct")
	public ResponseEntity<CartDTO> addProductToCart(@RequestParam("productId") long id) {
//		String token ="";
//		User u = getUserByToken()
		User u = uService.findUserByID("18130233@st.hcmuaf.edu.vn");
		//lay cartitem xem thu no co chua id book chua neu roi tang so luong len 1, chua thi them dong moi
		Cart cart = cartSer.getCartById(u.getUserCart().getId()).get();
		Set<CartItem> cartItem = cart.getCartItems();
		for (CartItem c : cartItem) {
			if(c.getBook().getId()==id) {
				c.setQuantity(c.getQuantity()+1);
			}else {
				
//				cartItemService.saveCartItem()
			}
		}
		
		
		return ResponseEntity.ok(null);
	}

	
	// xoa san pham trong gio hang
	@GetMapping("/removeProduct")
	public ResponseEntity<Boolean> removeProductToCart(@PathVariable String userId) {

		return ResponseEntity.ok(false);
	}
	
	
	
	
}
