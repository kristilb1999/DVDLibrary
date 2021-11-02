package com.mthree.dvdlibrary;

import com.mthree.dvdlibrary.controller.DVDLibraryController;
import com.mthree.dvdlibrary.dao.DVDLibraryDao;
import com.mthree.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mthree.dvdlibrary.dao.UpgradedDvdLibraryDao;
import com.mthree.dvdlibrary.dao.UpgradedDvdLibraryDaoFileImpl;
import com.mthree.dvdlibrary.ui.DVDLibraryView;
import com.mthree.dvdlibrary.ui.UserIO;
import com.mthree.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        DVDLibraryView myView = new DVDLibraryView(myIo);
//        //DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
//        UpgradedDvdLibraryDao myDao = new UpgradedDvdLibraryDaoFileImpl();
//        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
//        controller.run();

        //with Sprint DI -- xml file used
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        DVDLibraryController controller =
                ctx.getBean("controller", DVDLibraryController.class);
        controller.run();

    }

}
