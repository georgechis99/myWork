input metauml;
beginfig(1);
    Actor.user("User");
    Usecase.in("Inputs simulation parameters");
    Usecase.out("Gets simulation results");  
    out.top = in.bottom - 20;
    in.left = user.right + 20;
    out.left = user.right + 20;
    drawObjects(user,in,out);
    clink(inheritance)(user.human,in);
	clink(inheritance)(out,user.human);
endfig;
end