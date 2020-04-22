package builders;

import model.Seance;
import model.Ticket;

public class TicketBuilder {
    private Seance seance;
    private  int rowNumber;
    private  int seatNumber;


        public static TicketBuilder newInstance() {
            return new TicketBuilder();
        }

        private TicketBuilder() {
        }

        public TicketBuilder setRowNumber(int rowNumber){
            this.rowNumber = rowNumber;
            return this;
        }
        public TicketBuilder setSeatNumber(int seatNumber){
            this.seatNumber = seatNumber;
            return this;
        }

    public TicketBuilder setSeance(Seance seance){
        this.seance = seance;
        return this;
    }

        public Ticket build(){
            Ticket ticket = new Ticket();
            ticket.rowNumber = this.rowNumber;
            ticket.seatNumber = this.seatNumber;
            ticket.seance = this.seance;
            return ticket;
        }



}
