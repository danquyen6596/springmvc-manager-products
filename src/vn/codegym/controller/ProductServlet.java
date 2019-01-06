package vn.codegym.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import vn.codegym.model.Product;
import vn.codegym.services.ProductService;
import vn.codegym.services.ProductSeviceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerError;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductSeviceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null){
            action = "";
        }

        switch (action){
            case "create":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
                default:
                    break;
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null){
            action = "";
        }

        switch (action){
            case "create":
                insertForm(request, response);
                break;
            case "update":
                updateForm(request, response);
                break;
            case "delete":
                deleteForm(request, response);
                break;
            case "view":
                viewForm(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = this.productService.findAll();
        request.setAttribute("products", productList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/product_list.jsp");

        try{
            requestDispatcher.forward(request,response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void insertForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/product_insert.jsp");

        try {
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String madein = request.getParameter("madein");
        int code =(int) (Math.random() * 10000);

        Product product = new Product(code, name, price, madein);
        this.productService.save(product);

        request.setAttribute("message", "Insert success!");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/product_insert.jsp");

        try{
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) {
        int code = Integer.parseInt(request.getParameter("code"));

        Product product = this.productService.findByCode(code);

        RequestDispatcher requestDispatcher;

        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product", product);
            requestDispatcher =  request.getRequestDispatcher("product/product_update.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int code = Integer.parseInt(request.getParameter("code"));
        String name = request.getParameter("name");
        String madein = request.getParameter("madein");
        float price = Float.parseFloat(request.getParameter("price"));

        Product product = this.productService.findByCode(code);

        RequestDispatcher requestDispatcher;

        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            product.setName(name);
            product.setPrice(price);
            product.setMadein(madein);

            this.productService.update(code, product);
            request.setAttribute("product", product);
            request.setAttribute("message", "Update Success!");
            requestDispatcher = request.getRequestDispatcher("product/product_update.jsp");
        }

        try {
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void deleteForm(HttpServletRequest request, HttpServletResponse response) {
        int code = Integer.parseInt(request.getParameter("code"));

        Product product = this.productService.findByCode(code);

        RequestDispatcher requestDispatcher;

        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("product/product_delete.jsp");
        }

        try{
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int code = Integer.parseInt(request.getParameter("code"));

        Product product =  this.productService.findByCode(code);

        RequestDispatcher requestDispatcher;

        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            this.productService.delete(code);
            try {
                response.sendRedirect("/products");
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private void viewForm(HttpServletRequest request, HttpServletResponse response) {
        int code = Integer.parseInt(request.getParameter("code"));
        Product product = this.productService.findByCode(code);
        RequestDispatcher requestDispatcher;

        if(product == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("product", product);
            requestDispatcher = request.getRequestDispatcher("product/product_view.jsp");
        }

        try {
            requestDispatcher.forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
