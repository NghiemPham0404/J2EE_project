package com.example.J2EE_project.controllers;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * TODO : Thêm mới một tài khoản
     */
    @PreAuthorize("hasAuthority('Account Management create')")
    @Operation(summary = "Thêm mới một tài khoản")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        return ResponseBuilder.buildResponse(accountService.create(account), HttpStatus.OK);
    }

    /**
     * TODO : Sửa một tài khoản
     */
    @PreAuthorize("hasAuthority('Account Management update')")
    @PutMapping("/{id}")
    @Operation(summary = "Sửa một tài khoản")
    @ApiResponse(responseCode = "200", description = "Account updated successfully")
    public ResponseEntity<Object> updateAccount(@PathVariable Integer id, @RequestBody Account updatedAccount) {
        accountService.get(id);
        return ResponseBuilder.buildResponse(accountService.update(updatedAccount), HttpStatus.OK);
    }

    /**
     * TODO : Xóa một tài khoản
     */
    @PreAuthorize("hasAuthority('Account Management delete')")
    @Operation(summary = " Xóa một tài khoản")
    @ApiResponse(responseCode = "200", description = "Account deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Integer id) {
        return ResponseBuilder.buildResponse(accountService.delete(id), HttpStatus.OK);
    }

    /**
     * TODO : Lấy tài khoản theo id
     */
    @PreAuthorize("hasAuthority('Account Management read')")
    @Operation(summary = "Lấy tài khoản theo id")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved account")
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Integer id) {
        return accountService.get(id);
    }

    /**
     * TODO : Lấy tất cả tài khoản theo trang cho admin
     */

    @PreAuthorize("hasAuthority('Account Management read')")
    @Operation(summary = "Lấy tất cả tài khoản theo trang cho admin")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts")
    @GetMapping("/all")
    public ResponseEntity<Object> listAllAccount(@PathParam("admin_id") Integer adminId, @RequestParam(value = "page", defaultValue = "0") int page) {
        adminId = adminId == null ? 1 : adminId;
        return ResponseBuilder.buildResponse(accountService.listAllAccount(adminId, page), HttpStatus.OK);
    }

    /**
     * TODO : Lấy tất cả tài khoản theo tên
     */
    @PreAuthorize("hasAuthority('Account Management read')")
    @GetMapping("/search")
    @Operation(summary = "Lấy tất cả tài khoản theo tên")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts")
    public ResponseEntity<Object> listAllAccountByName(@PathParam("query") String query, @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseBuilder.buildResponse(accountService.listByName(query, page), HttpStatus.OK);
    }

    /**
     * TODO : Lấy tất cả tài khoản theo role
     */
    @PreAuthorize("hasAuthority('Account Management read')")
    @Operation(summary = "Lấy tất cả tài khoản theo role")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts")
    @GetMapping("/all/role")
    public ResponseEntity<Object> listAllAccountByRole(@PathParam("role") int role, @RequestParam(value = "page", defaultValue = "0") int page) {
        return ResponseBuilder.buildResponse(accountService.listByRole(role, page), HttpStatus.OK);
    }

    /**
     * TODO : Khóa tài khoản
     */
    @PreAuthorize("hasAuthority('Account Management update')")
    @Operation(summary = "Khóa tài khoản")
    @ApiResponse(responseCode = "200", description = "Account deactivated successfully")
    @PutMapping("deactivate/{id}")
    public void deactivateAccount(@PathVariable Integer id) {
        accountService.deactivateAccount(id);
    }

    /**
     * TODO : Mở khóa tài khoản
     */
    @PreAuthorize("hasAuthority('Account Management update')")
    @Operation(summary = " Mở khóa tài khoản")
    @ApiResponse(responseCode = "200", description = "Account activated successfully")
    @PutMapping("activate/{id}")
    public void activateAccount(@PathVariable Integer id) {
        accountService.activateAccount(id);
    }

}
