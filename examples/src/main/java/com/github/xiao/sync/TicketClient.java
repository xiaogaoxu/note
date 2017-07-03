package com.github.xiao.sync;
/**
 * Copyright (C) 2017 Feifan, Inc. All Rights Reserved.
 */

/**
 * @auther 肖高许
 * @date 2017/6/22
 * @version 1.0
 */
public class TicketClient extends Thread {

    private  Ticket ticket;

    public TicketClient(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (ticket.getNumbers()!=0){
            ticket.allocte();
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.setNumbers(100);

        TicketClient t1 = new TicketClient(ticket);
        TicketClient t2 = new TicketClient(ticket);
        TicketClient t3 = new TicketClient(ticket);
        TicketClient t4 = new TicketClient(ticket);
        TicketClient t5 = new TicketClient(ticket);
        t1.start();
        ticket.inc();
    }
}
