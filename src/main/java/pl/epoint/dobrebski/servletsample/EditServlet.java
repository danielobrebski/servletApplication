package pl.epoint.dobrebski.servletsample;

/**
 * Created by dobrebski on 04.01.17.
 */

import pl.epoint.dobrebski.model.Product;
import pl.epoint.dobrebski.utils.Constants;
import pl.epoint.dobrebski.utils.EditState;
import pl.epoint.dobrebski.utils.ProductMemoryManagerImpl;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name="editServlet", urlPatterns={"/edit"})
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EditState state = EditState.BAD_STATE;

    @Inject
    private ProductMemoryManagerImpl productMemoryManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        setState(request);
        doMemoryTasks(request, response);
    }

    private void setState(HttpServletRequest request) throws ServletException {
        if(request.getParameter("addSave") != null) {
            boolean addSave = Boolean.parseBoolean(request.getParameter("addSave"));
            if(addSave == true) {
                state = EditState.ADD_SAVE;
            } else {
                state = EditState.ADD;
            }
        } else if(request.getParameter("editSave") != null) {
            boolean editSave = Boolean.parseBoolean(request.getParameter("editSave"));
            if(editSave == true) {
                state = EditState.EDIT_SAVE;
            } else {
                state = EditState.EDIT;
            }
        } else if(request.getParameter("delete") != null) {
            boolean delete = Boolean.parseBoolean(request.getParameter("delete"));
            if (delete == true) {
                state = EditState.REMOVE;
            }
        }

        if(state == EditState.BAD_STATE)
            throw new ServletException(Constants.SERVLET_EXCEPTION_TEXT);
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        if(state == EditState.REMOVE) {
            return productMemoryManager.getProductByPK(Integer.parseInt(request.getParameter("pk")));
        } else {
            Product product = new Product();
            product.setPK(Integer.parseInt(request.getParameter("pk")));
            product.setName(request.getParameter("name"));
            product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            product.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))));
            return product;
        }
    }

    private void doMemoryTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(state == EditState.ADD) {
            RequestDispatcher rd = request.getRequestDispatcher(Constants.ADD_PRODUCT_JSP);
            rd.forward(request, response);
        } else if (state == EditState.EDIT) {
            RequestDispatcher rd = request.getRequestDispatcher(Constants.EDIT_PRODUCT_JSP);
            setAttributesForProductEdit(request);
            rd.forward(request, response);
        } else if (state == EditState.REMOVE) {
            Product product = getProductFromRequest(request);
            productMemoryManager.deleteProduct(product);
            getBackToProductListScreen(request, response);
        } else if (state == EditState.ADD_SAVE) {
            Product product = getProductFromRequest(request);
            productMemoryManager.insertProduct(product);
            getBackToProductListScreen(request, response);
        } else if (state == EditState.EDIT_SAVE) {
            Product product = getProductFromRequest(request);
            productMemoryManager.updateProduct(product);
            getBackToProductListScreen(request, response);
        }
    }

    private void setAttributesForProductEdit(HttpServletRequest request) {
        Integer PK = Integer.parseInt(request.getParameter("pk"));
        Product product = productMemoryManager.getProductByPK(PK);
        request.setAttribute("pk", product.getPK());
        request.setAttribute("name", product.getName());
        request.setAttribute("quantity", product.getQuantity());
        request.setAttribute("price", product.getPrice());
    }

    private void getBackToProductListScreen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(Constants.MANAGE_PRODUCTS_JSP);
        request.setAttribute("counter", ListServlet.hitCounter);
        rd.forward(request, response);
    }
}
