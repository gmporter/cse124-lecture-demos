/*
 * A demo of DNS resolution in Java
 * George Porter <gmporter@cs.ucsd.edu>
 */
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.UnknownHostException;

class ShowIP 
{ 
    public static void main(String args[]) 
    { 
        if (args.length != 1) {
			System.err.println("Usage: ShowIP <hostname>");
			System.exit(1);
		}

		String hostname = args[0];

		try {
			InetAddress[] addrs = InetAddress.getAllByName(hostname);

			System.out.println("IP addresses for host " + hostname);

			for (InetAddress addr : addrs) {
				if (addr instanceof Inet4Address) {
					System.out.println("\t IPv4: " + addr.getHostAddress());
				} else {
					System.out.println("\t IPv6: " + addr.getHostAddress());
				}
			}

		} catch (UnknownHostException uhe) {
			System.err.println("Unknown host: " + hostname);
			System.exit(1);
		}
    } 
} 

