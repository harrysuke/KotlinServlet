import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.annotation.WebFilter

//@WebFilter(urlPatterns = ["/user-list", "/add-user", "/search-form", "/api", "/pdf-report"])
class AuthFilter : Filter {
    override fun init(config: FilterConfig) {} // Type is required

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        
        val session = req.getSession(false)
        if (session?.getAttribute("user") == null) {
            res.sendRedirect("login.jsp")
        } else {
            chain.doFilter(request, response)
        }
    }

    override fun destroy() {}
}
