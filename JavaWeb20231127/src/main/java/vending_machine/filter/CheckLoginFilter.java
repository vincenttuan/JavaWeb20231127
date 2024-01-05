package vending_machine.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(value = {"/vending/main", "/vending/submit"})
public class CheckLoginFilter {

}
