package org.spo.trx.pg.cmd;

public class PG01O
{
	private String PAGE_SUBTITLE;

	private String PAGE_TITLE;

	private Main_section main_section;

	private int link_qtt;

	public String getPAGE_SUBTITLE ()
	{
		return PAGE_SUBTITLE;
	}

	public void setPAGE_SUBTITLE (String PAGE_SUBTITLE)
	{
		this.PAGE_SUBTITLE = PAGE_SUBTITLE;
	}

	public String getPAGE_TITLE ()
	{
		return PAGE_TITLE;
	}

	public void setPAGE_TITLE (String PAGE_TITLE)
	{
		this.PAGE_TITLE = PAGE_TITLE;
	}

	public Main_section getMain_section ()
	{
		return main_section;
	}

	public void setMain_section (Main_section main_section)
	{
		this.main_section = main_section;
	}

	public int getLink_qtt ()
	{
		return link_qtt;
	}

	public void setLink_qtt (int link_qtt)
	{
		this.link_qtt = link_qtt;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [PAGE_SUBTITLE = "+PAGE_SUBTITLE+", PAGE_TITLE = "+PAGE_TITLE+", main_section = "+main_section+", link_qtt = "+link_qtt+"]";
	}
}


 class Main_section
{
   private String more_pages_link_ic;

   private String section_title;

   private String section_links_att;

   private String section_id;

   private Section_links section_links;

   public String getMore_pages_link_ic ()
   {
       return more_pages_link_ic;
   }

   public void setMore_pages_link_ic (String more_pages_link_ic)
   {
       this.more_pages_link_ic = more_pages_link_ic;
   }

   public String getSection_title ()
   {
       return section_title;
   }

   public void setSection_title (String section_title)
   {
       this.section_title = section_title;
   }

   public String getSection_links_att ()
   {
       return section_links_att;
   }

   public void setSection_links_att (String section_links_att)
   {
       this.section_links_att = section_links_att;
   }

   public String getSection_id ()
   {
       return section_id;
   }

   public void setSection_id (String section_id)
   {
       this.section_id = section_id;
   }

   public Section_links getSection_links ()
   {
       return section_links;
   }

   public void setSection_links (Section_links section_links)
   {
       this.section_links = section_links;
   }

   @Override
   public String toString()
   {
       return "ClassPojo [more_pages_link_ic = "+more_pages_link_ic+", section_title = "+section_title+", section_links_att = "+section_links_att+", section_id = "+section_id+", section_links = "+section_links+"]";
   }
}
	

 class Section_links
{
private String link_sub_title;

private String link_id;

private String link_title;

public String getLink_sub_title ()
{
   return link_sub_title;
}

public void setLink_sub_title (String link_sub_title)
{
   this.link_sub_title = link_sub_title;
}

public String getLink_id ()
{
   return link_id;
}

public void setLink_id (String link_id)
{
   this.link_id = link_id;
}

public String getLink_title ()
{
   return link_title;
}

public void setLink_title (String link_title)
{
   this.link_title = link_title;
}

@Override
public String toString()
{
   return "ClassPojo [link_sub_title = "+link_sub_title+", link_id = "+link_id+", link_title = "+link_title+"]";
}
}
		
			
