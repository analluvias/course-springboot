package com.example.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_Product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	@ManyToMany //anotação para informar uma ligacao muitos para muitos
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id")) //tabela de ligacao(nometabelaligacao, chave estrangeira do produto, chave estrangeira de categoria)
	private Set<Category> categories = new HashSet<>();     //é importante lembrar de colocar uma ref em categories para esse mapeamento da tabela de ligacao
	
	//associa todas as linhas em order item que tenham o mesmo id que esse produto
	@OneToMany(mappedBy = "id.product") //id está eh orderItem e product eh atributo do id
	private Set<OrderItem> items = new HashSet<>();

	public Product() {
		super();
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public Set<Category> getCategories() {
		return categories;
	}
	
	//método que vai retornar os pedidos de que esse produto faz parte
	// mas buscando a partir da tabela de associação orderItem
	@JsonIgnore //essa anotação impede que quando esse produto for chamada da classe order, ele chame order de novo e entre em um loop
				//além de impedir que ao chamar product ele retorne para o serviço(postman) os pedidos de que product faz parte
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		
		//vamos rodas a lista de associação aqui e selecionar apenas os pedidos em que esse produto esrá
		for (OrderItem x : items) {
			set.add(x.getOrder());
		}
		
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
