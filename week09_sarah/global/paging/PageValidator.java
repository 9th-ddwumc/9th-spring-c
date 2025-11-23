package com.example.umc9th2.global.paging;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageValidator implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory)
            throws Exception {

        String pageStr = webRequest.getParameter("page");

        if (pageStr == null) {
            throw new IllegalArgumentException("page 파라미터가 없습니다.");
        }

        int page = Integer.parseInt(pageStr);

        if (page < 1) {
            throw new IllegalArgumentException("page는 1 이상이어야 합니다.");
        }

        return page - 1; // JPA는 0부터 시작
    }
}
