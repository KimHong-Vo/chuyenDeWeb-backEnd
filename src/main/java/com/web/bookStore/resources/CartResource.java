package com.web.bookStore.resources;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookStore.dto.BookDTO;
import com.web.bookStore.dto.CartDTO;
import com.web.bookStore.dto.CartItemDTO;
import com.web.bookStore.entities.Book;
import com.web.bookStore.entities.Cart;
import com.web.bookStore.entities.CartItem;
import com.web.bookStore.entities.User;
import com.web.bookStore.request.ProductRequest;
import com.web.bookStore.services.BookService;
import com.web.bookStore.services.CartItemService;
import com.web.bookStore.services.CartService;
import com.web.bookStore.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
		System.out.println("cart item number: " + cart.getCartItems().size());
		
		for (CartItem c : cartItem) {
			System.out.println("cart item null?: " + c==null);
			BookDTO bDTO = new BookDTO(c.getBook().getId(), c.getBook().getTitle(), c.getBook().getOurPrice(),
					c.getBook().getPicturePath());
			System.out.println(c.getBook().getPicturePath()+" bookdto:link picture");
			System.out.println(bDTO.getPicturePath()+" bookdto:link picture");
			CartItemDTO cDTO = new CartItemDTO(c.getQuantity(), bDTO);
			cDTO.setId(c.getId());
			cartItemDTO.add(cDTO);
		}
		CartDTO cartDTO = new CartDTO(cart.getId(), cartItemDTO);
		return ResponseEntity.ok(cartDTO);
	}

	// pt them san pham vao gio hang
	@GetMapping("/addCart/{bookID}")
	public ResponseEntity<String> addProductToCart(
			@PathVariable long bookID) {
//		String token ="";
//		User u = getUserByToken()
//	    if(u==null) return "login";
//	    else (do the following tasks)
		User u = uService.findUserByID("18130233@st.hcmuaf.edu.vn");// this is u above
		// lay cartitem xem thu no co chua id book chua neu roi tang so luong len 1,
		// chua thi them dong moi
		Cart cart = cartSer.getCartById(u.getUserCart().getId()).get();
		Set<CartItem> cartItem = cart.getCartItems();
		System.out.println(cartItem.toString());
		Book book = bookService.findOne(bookID).get();
		if(cartItem.size()==0) {
			CartItem item = new CartItem();
			item.setQuantity(1);
			item.setBook(book);
			item.setCart(cart);
			cartItemService.saveCartItem(item);
			// update book qunaties in stock
			book.setInStockNumber(book.getInStockNumber() - 1);
			bookService.update(book);
			return ResponseEntity.ok("Sản phẩm đã được thêm vào Giỏ hàng");
		}
		for (CartItem c : cartItem) {
			if (c.getBook().getId() == bookID) {
				// update book quantites in cartItem
				c.setQuantity(c.getQuantity() + 1);
				cartItemService.update(c);
				// update book qunaties in stock
				book.setInStockNumber(book.getInStockNumber() - 1);
				bookService.update(book);
				return ResponseEntity.ok("Sản phẩm đã được thêm vào Giỏ hàng");
			} else {
				CartItem item = new CartItem();
				item.setQuantity(1);
				item.setBook(book);
				item.setCart(cart);
				cartItemService.saveCartItem(item);
				// update book qunaties in stock
				book.setInStockNumber(book.getInStockNumber() - 1);
				bookService.update(book);
				return ResponseEntity.ok("Sản phẩm đã được thêm vào Giỏ hàng");
			}
		}
		return ResponseEntity.ok("Sản phẩm không được thêm vào Giỏ hàng");
	}

	// xoa san pham trong gio hang
	@GetMapping("/removeProduct/{idItem}")
	public ResponseEntity<Boolean> removeProductToCart(
			@PathVariable long idItem) {
//		String token ="";
//		User u = getUserByToken()
//	    if(u==null) return "login";
//	    else (do the following tasks)
		User u = uService.findUserByID("18130233@st.hcmuaf.edu.vn");// this is u above
		CartItem c = cartItemService.getCartItem(idItem);
		Book b = c.getBook();
		try {
			b.setInStockNumber(b.getInStockNumber() + c.getQuantity());
			bookService.update(b);
			cartItemService.remove(idItem);
		} catch (Exception e) {
			System.out.println(false);
		}
		return ResponseEntity.ok(true);
	}

}
