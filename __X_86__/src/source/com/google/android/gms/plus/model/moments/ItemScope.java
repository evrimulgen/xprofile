// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.im;
import java.util.*;

public interface ItemScope
    extends Freezable
{
    public static class Builder
    {

        public ItemScope build()
        {
            return new im(RM, RN, RO, RP, RQ, RR, RS, RT, RU, RV, RW, RX, RY, RZ, Sa, Sb, Sc, lt, Sd, Se, Sf, Sg, FH, Sh, Si, Sj, Sk, Sl, Sm, Sn, So, uS, Sp, Sq, KX, Sr, KY, mName, Ss, St, Su, Sv, Sw, Sx, Sy, Sz, SA, SB, SC, SD, SE, Oc, pS, SF, SG);
        }

        public Builder setAbout(ItemScope itemscope)
        {
            RN = (im)itemscope;
            RM.add(Integer.valueOf(2));
            return this;
        }

        public Builder setAdditionalName(List list)
        {
            RO = list;
            RM.add(Integer.valueOf(3));
            return this;
        }

        public Builder setAddress(ItemScope itemscope)
        {
            RP = (im)itemscope;
            RM.add(Integer.valueOf(4));
            return this;
        }

        public Builder setAddressCountry(String s)
        {
            RQ = s;
            RM.add(Integer.valueOf(5));
            return this;
        }

        public Builder setAddressLocality(String s)
        {
            RR = s;
            RM.add(Integer.valueOf(6));
            return this;
        }

        public Builder setAddressRegion(String s)
        {
            RS = s;
            RM.add(Integer.valueOf(7));
            return this;
        }

        public Builder setAssociated_media(List list)
        {
            RT = list;
            RM.add(Integer.valueOf(8));
            return this;
        }

        public Builder setAttendeeCount(int i)
        {
            RU = i;
            RM.add(Integer.valueOf(9));
            return this;
        }

        public Builder setAttendees(List list)
        {
            RV = list;
            RM.add(Integer.valueOf(10));
            return this;
        }

        public Builder setAudio(ItemScope itemscope)
        {
            RW = (im)itemscope;
            RM.add(Integer.valueOf(11));
            return this;
        }

        public Builder setAuthor(List list)
        {
            RX = list;
            RM.add(Integer.valueOf(12));
            return this;
        }

        public Builder setBestRating(String s)
        {
            RY = s;
            RM.add(Integer.valueOf(13));
            return this;
        }

        public Builder setBirthDate(String s)
        {
            RZ = s;
            RM.add(Integer.valueOf(14));
            return this;
        }

        public Builder setByArtist(ItemScope itemscope)
        {
            Sa = (im)itemscope;
            RM.add(Integer.valueOf(15));
            return this;
        }

        public Builder setCaption(String s)
        {
            Sb = s;
            RM.add(Integer.valueOf(16));
            return this;
        }

        public Builder setContentSize(String s)
        {
            Sc = s;
            RM.add(Integer.valueOf(17));
            return this;
        }

        public Builder setContentUrl(String s)
        {
            lt = s;
            RM.add(Integer.valueOf(18));
            return this;
        }

        public Builder setContributor(List list)
        {
            Sd = list;
            RM.add(Integer.valueOf(19));
            return this;
        }

        public Builder setDateCreated(String s)
        {
            Se = s;
            RM.add(Integer.valueOf(20));
            return this;
        }

        public Builder setDateModified(String s)
        {
            Sf = s;
            RM.add(Integer.valueOf(21));
            return this;
        }

        public Builder setDatePublished(String s)
        {
            Sg = s;
            RM.add(Integer.valueOf(22));
            return this;
        }

        public Builder setDescription(String s)
        {
            FH = s;
            RM.add(Integer.valueOf(23));
            return this;
        }

        public Builder setDuration(String s)
        {
            Sh = s;
            RM.add(Integer.valueOf(24));
            return this;
        }

        public Builder setEmbedUrl(String s)
        {
            Si = s;
            RM.add(Integer.valueOf(25));
            return this;
        }

        public Builder setEndDate(String s)
        {
            Sj = s;
            RM.add(Integer.valueOf(26));
            return this;
        }

        public Builder setFamilyName(String s)
        {
            Sk = s;
            RM.add(Integer.valueOf(27));
            return this;
        }

        public Builder setGender(String s)
        {
            Sl = s;
            RM.add(Integer.valueOf(28));
            return this;
        }

        public Builder setGeo(ItemScope itemscope)
        {
            Sm = (im)itemscope;
            RM.add(Integer.valueOf(29));
            return this;
        }

        public Builder setGivenName(String s)
        {
            Sn = s;
            RM.add(Integer.valueOf(30));
            return this;
        }

        public Builder setHeight(String s)
        {
            So = s;
            RM.add(Integer.valueOf(31));
            return this;
        }

        public Builder setId(String s)
        {
            uS = s;
            RM.add(Integer.valueOf(32));
            return this;
        }

        public Builder setImage(String s)
        {
            Sp = s;
            RM.add(Integer.valueOf(33));
            return this;
        }

        public Builder setInAlbum(ItemScope itemscope)
        {
            Sq = (im)itemscope;
            RM.add(Integer.valueOf(34));
            return this;
        }

        public Builder setLatitude(double d)
        {
            KX = d;
            RM.add(Integer.valueOf(36));
            return this;
        }

        public Builder setLocation(ItemScope itemscope)
        {
            Sr = (im)itemscope;
            RM.add(Integer.valueOf(37));
            return this;
        }

        public Builder setLongitude(double d)
        {
            KY = d;
            RM.add(Integer.valueOf(38));
            return this;
        }

        public Builder setName(String s)
        {
            mName = s;
            RM.add(Integer.valueOf(39));
            return this;
        }

        public Builder setPartOfTVSeries(ItemScope itemscope)
        {
            Ss = (im)itemscope;
            RM.add(Integer.valueOf(40));
            return this;
        }

        public Builder setPerformers(List list)
        {
            St = list;
            RM.add(Integer.valueOf(41));
            return this;
        }

        public Builder setPlayerType(String s)
        {
            Su = s;
            RM.add(Integer.valueOf(42));
            return this;
        }

        public Builder setPostOfficeBoxNumber(String s)
        {
            Sv = s;
            RM.add(Integer.valueOf(43));
            return this;
        }

        public Builder setPostalCode(String s)
        {
            Sw = s;
            RM.add(Integer.valueOf(44));
            return this;
        }

        public Builder setRatingValue(String s)
        {
            Sx = s;
            RM.add(Integer.valueOf(45));
            return this;
        }

        public Builder setReviewRating(ItemScope itemscope)
        {
            Sy = (im)itemscope;
            RM.add(Integer.valueOf(46));
            return this;
        }

        public Builder setStartDate(String s)
        {
            Sz = s;
            RM.add(Integer.valueOf(47));
            return this;
        }

        public Builder setStreetAddress(String s)
        {
            SA = s;
            RM.add(Integer.valueOf(48));
            return this;
        }

        public Builder setText(String s)
        {
            SB = s;
            RM.add(Integer.valueOf(49));
            return this;
        }

        public Builder setThumbnail(ItemScope itemscope)
        {
            SC = (im)itemscope;
            RM.add(Integer.valueOf(50));
            return this;
        }

        public Builder setThumbnailUrl(String s)
        {
            SD = s;
            RM.add(Integer.valueOf(51));
            return this;
        }

        public Builder setTickerSymbol(String s)
        {
            SE = s;
            RM.add(Integer.valueOf(52));
            return this;
        }

        public Builder setType(String s)
        {
            Oc = s;
            RM.add(Integer.valueOf(53));
            return this;
        }

        public Builder setUrl(String s)
        {
            pS = s;
            RM.add(Integer.valueOf(54));
            return this;
        }

        public Builder setWidth(String s)
        {
            SF = s;
            RM.add(Integer.valueOf(55));
            return this;
        }

        public Builder setWorstRating(String s)
        {
            SG = s;
            RM.add(Integer.valueOf(56));
            return this;
        }

        private String FH;
        private double KX;
        private double KY;
        private String Oc;
        private final Set RM = new HashSet();
        private im RN;
        private List RO;
        private im RP;
        private String RQ;
        private String RR;
        private String RS;
        private List RT;
        private int RU;
        private List RV;
        private im RW;
        private List RX;
        private String RY;
        private String RZ;
        private String SA;
        private String SB;
        private im SC;
        private String SD;
        private String SE;
        private String SF;
        private String SG;
        private im Sa;
        private String Sb;
        private String Sc;
        private List Sd;
        private String Se;
        private String Sf;
        private String Sg;
        private String Sh;
        private String Si;
        private String Sj;
        private String Sk;
        private String Sl;
        private im Sm;
        private String Sn;
        private String So;
        private String Sp;
        private im Sq;
        private im Sr;
        private im Ss;
        private List St;
        private String Su;
        private String Sv;
        private String Sw;
        private String Sx;
        private im Sy;
        private String Sz;
        private String lt;
        private String mName;
        private String pS;
        private String uS;

        public Builder()
        {
        }
    }


    public abstract ItemScope getAbout();

    public abstract List getAdditionalName();

    public abstract ItemScope getAddress();

    public abstract String getAddressCountry();

    public abstract String getAddressLocality();

    public abstract String getAddressRegion();

    public abstract List getAssociated_media();

    public abstract int getAttendeeCount();

    public abstract List getAttendees();

    public abstract ItemScope getAudio();

    public abstract List getAuthor();

    public abstract String getBestRating();

    public abstract String getBirthDate();

    public abstract ItemScope getByArtist();

    public abstract String getCaption();

    public abstract String getContentSize();

    public abstract String getContentUrl();

    public abstract List getContributor();

    public abstract String getDateCreated();

    public abstract String getDateModified();

    public abstract String getDatePublished();

    public abstract String getDescription();

    public abstract String getDuration();

    public abstract String getEmbedUrl();

    public abstract String getEndDate();

    public abstract String getFamilyName();

    public abstract String getGender();

    public abstract ItemScope getGeo();

    public abstract String getGivenName();

    public abstract String getHeight();

    public abstract String getId();

    public abstract String getImage();

    public abstract ItemScope getInAlbum();

    public abstract double getLatitude();

    public abstract ItemScope getLocation();

    public abstract double getLongitude();

    public abstract String getName();

    public abstract ItemScope getPartOfTVSeries();

    public abstract List getPerformers();

    public abstract String getPlayerType();

    public abstract String getPostOfficeBoxNumber();

    public abstract String getPostalCode();

    public abstract String getRatingValue();

    public abstract ItemScope getReviewRating();

    public abstract String getStartDate();

    public abstract String getStreetAddress();

    public abstract String getText();

    public abstract ItemScope getThumbnail();

    public abstract String getThumbnailUrl();

    public abstract String getTickerSymbol();

    public abstract String getType();

    public abstract String getUrl();

    public abstract String getWidth();

    public abstract String getWorstRating();

    public abstract boolean hasAbout();

    public abstract boolean hasAdditionalName();

    public abstract boolean hasAddress();

    public abstract boolean hasAddressCountry();

    public abstract boolean hasAddressLocality();

    public abstract boolean hasAddressRegion();

    public abstract boolean hasAssociated_media();

    public abstract boolean hasAttendeeCount();

    public abstract boolean hasAttendees();

    public abstract boolean hasAudio();

    public abstract boolean hasAuthor();

    public abstract boolean hasBestRating();

    public abstract boolean hasBirthDate();

    public abstract boolean hasByArtist();

    public abstract boolean hasCaption();

    public abstract boolean hasContentSize();

    public abstract boolean hasContentUrl();

    public abstract boolean hasContributor();

    public abstract boolean hasDateCreated();

    public abstract boolean hasDateModified();

    public abstract boolean hasDatePublished();

    public abstract boolean hasDescription();

    public abstract boolean hasDuration();

    public abstract boolean hasEmbedUrl();

    public abstract boolean hasEndDate();

    public abstract boolean hasFamilyName();

    public abstract boolean hasGender();

    public abstract boolean hasGeo();

    public abstract boolean hasGivenName();

    public abstract boolean hasHeight();

    public abstract boolean hasId();

    public abstract boolean hasImage();

    public abstract boolean hasInAlbum();

    public abstract boolean hasLatitude();

    public abstract boolean hasLocation();

    public abstract boolean hasLongitude();

    public abstract boolean hasName();

    public abstract boolean hasPartOfTVSeries();

    public abstract boolean hasPerformers();

    public abstract boolean hasPlayerType();

    public abstract boolean hasPostOfficeBoxNumber();

    public abstract boolean hasPostalCode();

    public abstract boolean hasRatingValue();

    public abstract boolean hasReviewRating();

    public abstract boolean hasStartDate();

    public abstract boolean hasStreetAddress();

    public abstract boolean hasText();

    public abstract boolean hasThumbnail();

    public abstract boolean hasThumbnailUrl();

    public abstract boolean hasTickerSymbol();

    public abstract boolean hasType();

    public abstract boolean hasUrl();

    public abstract boolean hasWidth();

    public abstract boolean hasWorstRating();
}
