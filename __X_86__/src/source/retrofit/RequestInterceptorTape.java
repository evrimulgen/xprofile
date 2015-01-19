// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.util.*;

// Referenced classes of package retrofit:
//            RequestInterceptor

final class RequestInterceptorTape
    implements RequestInterceptor.RequestFacade, RequestInterceptor
{
    private static abstract class Command extends Enum
    {

        public static Command valueOf(String s)
        {
            return (Command)Enum.valueOf(retrofit/RequestInterceptorTape$Command, s);
        }

        public static Command[] values()
        {
            return (Command[])$VALUES.clone();
        }

        abstract void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1);

        private static final Command $VALUES[];
        public static final Command ADD_ENCODED_PATH_PARAM;
        public static final Command ADD_ENCODED_QUERY_PARAM;
        public static final Command ADD_HEADER;
        public static final Command ADD_PATH_PARAM;
        public static final Command ADD_QUERY_PARAM;

        static 
        {
            ADD_HEADER = new Command("ADD_HEADER", 0) {

                public void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1)
                {
                    requestfacade.addHeader(s, s1);
                }

            }
;
            ADD_PATH_PARAM = new Command("ADD_PATH_PARAM", 1) {

                public void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1)
                {
                    requestfacade.addPathParam(s, s1);
                }

            }
;
            ADD_ENCODED_PATH_PARAM = new Command("ADD_ENCODED_PATH_PARAM", 2) {

                public void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1)
                {
                    requestfacade.addEncodedPathParam(s, s1);
                }

            }
;
            ADD_QUERY_PARAM = new Command("ADD_QUERY_PARAM", 3) {

                public void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1)
                {
                    requestfacade.addQueryParam(s, s1);
                }

            }
;
            ADD_ENCODED_QUERY_PARAM = new Command("ADD_ENCODED_QUERY_PARAM", 4) {

                public void intercept(RequestInterceptor.RequestFacade requestfacade, String s, String s1)
                {
                    requestfacade.addEncodedQueryParam(s, s1);
                }

            }
;
            Command acommand[] = new Command[5];
            acommand[0] = ADD_HEADER;
            acommand[1] = ADD_PATH_PARAM;
            acommand[2] = ADD_ENCODED_PATH_PARAM;
            acommand[3] = ADD_QUERY_PARAM;
            acommand[4] = ADD_ENCODED_QUERY_PARAM;
            $VALUES = acommand;
        }

        private Command(String s, int i)
        {
            super(s, i);
        }

    }

    private static final class CommandWithParams
    {

        final Command command;
        final String name;
        final String value;

        CommandWithParams(Command command1, String s, String s1)
        {
            command = command1;
            name = s;
            value = s1;
        }
    }


    RequestInterceptorTape()
    {
    }

    public void addEncodedPathParam(String s, String s1)
    {
        tape.add(new CommandWithParams(Command.ADD_ENCODED_PATH_PARAM, s, s1));
    }

    public void addEncodedQueryParam(String s, String s1)
    {
        tape.add(new CommandWithParams(Command.ADD_ENCODED_QUERY_PARAM, s, s1));
    }

    public void addHeader(String s, String s1)
    {
        tape.add(new CommandWithParams(Command.ADD_HEADER, s, s1));
    }

    public void addPathParam(String s, String s1)
    {
        tape.add(new CommandWithParams(Command.ADD_PATH_PARAM, s, s1));
    }

    public void addQueryParam(String s, String s1)
    {
        tape.add(new CommandWithParams(Command.ADD_QUERY_PARAM, s, s1));
    }

    public void intercept(RequestInterceptor.RequestFacade requestfacade)
    {
        CommandWithParams commandwithparams;
        for(Iterator iterator = tape.iterator(); iterator.hasNext(); commandwithparams.command.intercept(requestfacade, commandwithparams.name, commandwithparams.value))
            commandwithparams = (CommandWithParams)iterator.next();

    }

    private final List tape = new ArrayList();
}
