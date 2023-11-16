package fr.energie.billing.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Person {
	
	public static final String CIVILITY_MALE = "M.";
	public static final String CIVILITY_FEMALE = "Mme.";
	
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false, length = 45)
    private String civility;

    @Column(length = 45)
    private String firstname;

    @Column(length = 45)
    private String lastname;

    @OneToOne(cascade = CascadeType.ALL ) 
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

	public Person(String civility, String firstname, String lastname, String reference) {
		this.civility = civility;
		this.firstname = firstname;
		this.lastname = lastname;
		this.customer = new Customer(reference);
	}
	
	@Override
	public String toString() {
		return "Person [" + civility + " " + firstname + " " + lastname + ", reference="
				+ customer.getReference() + "]";
	}

}
