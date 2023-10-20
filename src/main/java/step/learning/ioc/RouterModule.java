package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        // Третій спосіб конфігурування фільтрів та сервлетів - IoC
        filter("/*").through(CharsetFilter.class);

        serve("/").with(HomeServlet.class);
        serve("/db").with(DbServlet.class);
        serve("/ioc").with(IocServlet.class);
        serve("/jsp").with(JspServlet.class);
        serve("/filters").with(FiltersServlet.class);
        serve("/signup").with(SignupServlet.class);

    }
}
